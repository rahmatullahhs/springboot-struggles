package com.emranhss.merchandise.RestController;

import com.emranhss.merchandise.entity.Goods;
import com.emranhss.merchandise.repository.BrandRepo;
import com.emranhss.merchandise.repository.CategoryRepo;
import com.emranhss.merchandise.repository.GoodsRepo;
import com.emranhss.merchandise.service.BrandService;
import com.emranhss.merchandise.service.CategoryService;
import com.emranhss.merchandise.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/goods/")
@CrossOrigin("*")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GoodsRepo goodsRepo;
    @Autowired
    private BrandRepo brandRepo;
    @Autowired
    private CategoryRepo categoryRepo;


    // save
    @PostMapping("add")
    public Goods addGoods(@RequestBody Goods goods) {
        return goodsRepo.save(goods);
    }

    // Read all
    @GetMapping("")
    public List<Goods> getAllGoods() {
        return goodsRepo.findAll();
    }

    // Read one by ID
    @GetMapping("{id}")
    public Optional<Goods> getGoodsById(@PathVariable Long id) {
        return goodsRepo.findById(id);
    }

    // Update
    @PutMapping("{id}")
    public Goods updateGoods(@PathVariable Long id, @RequestBody Goods goodsDetails) {
        return goodsRepo.findById(id)
                .map(goods -> {
                    goods.setBrandId(goodsDetails.getBrandId());
                    goods.setCategoryId(goodsDetails.getCategoryId());
                    goods.setGoodsName(goodsDetails.getGoodsName());
                    goods.setDetails(goodsDetails.getDetails());
                    goods.setInvoice(goodsDetails.getInvoice());
                    goods.setSupplierId(goodsDetails.getSupplierId());
                    goods.setDate(goodsDetails.getDate());
                    goods.setQty(goodsDetails.getQty());
                    goods.setPrice(goodsDetails.getPrice());

                    goods.setPaid(goodsDetails.getPaid());
                    goods.setDue(goodsDetails.getDue());

                    return goodsRepo.save(goods);
                })
                .orElseThrow(() -> new RuntimeException("Goods not found with id: " + id));
    }

    // Delete
    @DeleteMapping("{id}")
    public void deleteGoods(@PathVariable Long id) {
        goodsRepo.deleteById(id);
    }
}
