package com.emranhss.merchandise.service;

import com.emranhss.merchandise.dto.UserResponseDTO;
import com.emranhss.merchandise.entity.Role;
import com.emranhss.merchandise.entity.RoleAdmin;
import com.emranhss.merchandise.entity.Token;
import com.emranhss.merchandise.entity.User;
import com.emranhss.merchandise.jwt.JwtService;
import com.emranhss.merchandise.repository.TokenRepo;
import com.emranhss.merchandise.repository.UserRepo;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TokenRepo tokenRepo;
    @Autowired
    private EmailService emailService;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private RoleAdminService roleAdminService;

    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    @Value("src/main/resources/static/images")
    private String uploadDir;



    public void saveOrUpdate(User user, MultipartFile imageFile) {

        if (imageFile != null && !imageFile.isEmpty()) {
            String filename = saveImage(imageFile, user);
            user.setPhoto(filename);
        }

        user.setRole(Role.ADMIN);
        userRepo.save(user);
//        sendActivationEmail(user);
    }


    public List<User> findAllUsers() {
        return userRepo.findAll();
    }



    public List<UserResponseDTO> getAllUsersResponseDTOS() {
        return userRepo.findAll().stream().map(user -> {
            UserResponseDTO dto = new UserResponseDTO();


            dto.setId(user.getId());
            dto.setEmail(user.getEmail());
            dto.setName(user.getName());
            dto.setPhoto(user.getPhoto());
            dto.setPhone(user.getPhone());

            return dto;
        }).toList();
    }





    public User findById(int id) {
        return userRepo.findById(id).get();
    }

    public void delete(User user) {
        userRepo.delete(user);
    }


    private void sendActivationEmail(User user) {
        String subject = "Welcome to Our Service – Confirm Your Registration";

        String activationLink = "http://localhost:8085/api/user/active/" + user.getId();

        String mailText = "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<style>"
                + "  body { font-family: Arial, sans-serif; line-height: 1.6; }"
                + "  .container { max-width: 600px; margin: auto; padding: 20px; border: 1px solid #e0e0e0; border-radius: 10px; }"
                + "  .header { background-color: #4CAF50; color: white; padding: 10px; text-align: center; border-radius: 10px 10px 0 0; }"
                + "  .content { padding: 20px; }"
                + "  .footer { font-size: 0.9em; color: #777; margin-top: 20px; text-align: center; }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "  <div class='container'>"
                + "    <div class='header'>"
                + "      <h2>Welcome to Our Platform</h2>"
                + "    </div>"
                + "    <div class='content'>"
                + "      <p>Dear " + user.getName() + ",</p>"
                + "      <p>Thank you for registering with us. We are excited to have you on board!</p>"
                + "      <p>Please confirm your email address to activate your account and get started.</p>"
                + "      <p>If you have any questions or need help, feel free to reach out to our support team.</p>"
                + "      <br>"
                + "      <p>Best regards,<br>The Support Team</p>"
                + "      <p>To Activate Your Account, please click the following link:</p>"
                + "      <p><a href=\"" + activationLink + "\">Activate Account</a></p>"
                + "    </div>"
                + "    <div class='footer'>"
                + "      &copy; " + java.time.Year.now() + " YourCompany. All rights reserved."
                + "    </div>"
                + "  </div>"
                + "</body>"
                + "</html>";

        try {
            emailService.sendSimpleEmail(user.getEmail(), subject, mailText);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send activation email", e);
        }
    }

    //  Admin Part start

    public String saveImageForAdmin(MultipartFile file, RoleAdmin roleAdmin) {

        Path uploadPath = Paths.get(uploadDir + "/roleAdmin");
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectory(uploadPath);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        String adminName = roleAdmin.getName();
        String fileName = adminName.trim().replaceAll("\\s+", "_");

        String savedFileName = fileName + "_" + UUID.randomUUID().toString();

        try {
            Path filePath = uploadPath.resolve(savedFileName);
            Files.copy(file.getInputStream(), filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return savedFileName;

    }


    public void registerAdmin(User user, MultipartFile imageFile, RoleAdmin adminData) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            // Save image for both User and Admin
            String fileName = saveImage(imageFile, user);
            String adminImage = saveImageForAdmin(imageFile, adminData);
            adminData.setPhoto(adminImage);
            user.setPhoto(fileName);
        }

        // Encode password before saving User
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ADMIN);
        user.setActive(true);

        // Save User FIRST and get persisted instance
        User savedUser = userRepo.save(user);

        // Now, associate saved User with Super Admin
        adminData.setUser(savedUser);
        roleAdminService.save(adminData);

        // Now generate token and save Token associated with savedUser
        String jwt = jwtService.generateToken(savedUser);
        saveUserToken(jwt, savedUser);

        // Send Activation Email
//        sendActivationEmail(savedUser);



    }



    // for User folder
    public String saveImage(MultipartFile file, User user) {

        Path uploadPath = Paths.get(uploadDir + "/users");
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectory(uploadPath);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        String fileName = user.getName() + "_" + UUID.randomUUID().toString();


        try {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileName;

    }



    private void saveUserToken(String jwt, User user) {
        Token token = new Token();
        token.setToken(jwt);
        token.setLogout(false);
        token.setUser(user);
        tokenRepo.save(token);
    }

    private void removeAllTokenByUser(User user) {

        List<Token> validTokens = tokenRepo.findAllTokenByUser(user.getId());

        if (validTokens.isEmpty()) {
            return;
        }
        validTokens.forEach(t -> {
            t.setLogout(true);
        });

        tokenRepo.saveAll(validTokens);

    }


}
