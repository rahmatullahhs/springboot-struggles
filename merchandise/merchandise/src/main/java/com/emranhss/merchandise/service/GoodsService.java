package com.emranhss.merchandise.service;

import com.emranhss.merchandise.entity.Goods;
import com.emranhss.merchandise.repository.GoodsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GoodsService {

    private final GoodsRepo goodsRepo;

    @Autowired
    public GoodsService(GoodsRepo goodsRepo) {
        this.goodsRepo = goodsRepo;
    }

    // ✅ Create a new Goods
    public Goods createGoods(Goods goods) {
        return goodsRepo.save(goods);
    }

    // ✅ Get all Goods
    public List<Goods> getAllGoods() {
        return goodsRepo.findAll();
    }

    // ✅ Get one Goods by ID
    public Optional<Goods> getGoodsById(Long id) {
        return goodsRepo.findById(id);
    }

    // ✅ Update an existing Goods
    public Optional<Goods> updateGoods(Long id, Goods updatedGoods) {
        return goodsRepo.findById(id).map(existingGoods -> {
            existingGoods.setBrandId(updatedGoods.getBrandId());
            existingGoods.setCategoryId(updatedGoods.getCategoryId());
            existingGoods.setGoodsName(updatedGoods.getGoodsName());
            existingGoods.setDetails(updatedGoods.getDetails());
            existingGoods.setInvoice(updatedGoods.getInvoice());
            existingGoods.setSupplierId(updatedGoods.getSupplierId());
            existingGoods.setDate(updatedGoods.getDate());
            existingGoods.setQty(updatedGoods.getQty());
            existingGoods.setPrice(updatedGoods.getPrice());

            existingGoods.setPaid(updatedGoods.getPaid());
            existingGoods.setDue(updatedGoods.getDue());
            return goodsRepo.save(existingGoods);
        });
    }

    // ✅ Delete Goods by ID
    public void deleteGoods(Long id) {
        goodsRepo.deleteById(id);
    }
}
