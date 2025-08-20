package com.emranhss.merchandise.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "balanceSheet")
public class BalanceSheet {
    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "total_buy_id", referencedColumnName = "id")
        private TotalBuy totalBuy;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "total_sale_id", referencedColumnName = "id")
        private TotalSale totalSale;

        public BalanceSheet() {}

        public BalanceSheet(TotalBuy totalBuy, TotalSale totalSale) {
            this.totalBuy = totalBuy;
            this.totalSale = totalSale;
        }

        public Long getId() {
            return id;
        }

        public TotalBuy getTotalBuy() {
            return totalBuy;
        }

        public void setTotalBuy(TotalBuy totalBuy) {
            this.totalBuy = totalBuy;
        }

        public TotalSale getTotalSale() {
            return totalSale;
        }

        public void setTotalSale(TotalSale totalSale) {
            this.totalSale = totalSale;
        }

        public double getProfitOrLoss() {
            if (totalBuy == null || totalSale == null) return 0;
            return totalSale.getAmount() - totalBuy.getAmount();
        }
    }
