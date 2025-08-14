package com.emranhss.merchandise.RestController;
import com.emranhss.merchandise.entity.Brand;
import com.emranhss.merchandise.repository.BrandRepo;
import com.emranhss.merchandise.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/brand")
@CrossOrigin("*")
public class BrandController {
    @Autowired
    private BrandService brandService;

    private final BrandRepo brandRepo;

    public BrandController(BrandRepo brandRepo) {
        this.brandRepo = brandRepo;
    }

    //create
    @PostMapping("add")
    public Brand addBrand(@RequestBody Brand brand) {

        return brandRepo.save(brand);
    }

    //read all
    @GetMapping("")
    public List<Brand> getAllBrand() {
        return brandRepo.findAll();
    }

    //read one
    @GetMapping("/{id}")
    public Optional<Brand> getAllBrandById(@PathVariable Long id) {
        return brandRepo.findById(id);
    }

    // Update
    @PutMapping("/{id}")
    public Brand updateBrand(@PathVariable Long id, @RequestBody Brand brandDetails) {
        return brandRepo.findById(id)
                .map(brand -> {
                    brand.setName(brandDetails.getName());

                    Brand updatedBrand = brandRepo.save(brand);
                    return brandRepo.save(updatedBrand);
                })
                .orElseThrow();
    }
    //delete
    @DeleteMapping("{id}")
    public  void  deleteBrand(@PathVariable Long id){
        brandRepo.deleteById(id);

    }


}
