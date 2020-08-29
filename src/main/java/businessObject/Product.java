package businessObject;

import java.util.List;

public class Product {
    private String category;
    public double maxPrice;
    public double price;
    public double minPrice;
    public int picsCnt;
    public int sale;
    public String promoTxt;
    public List<Size> sizes;
    public List<Integer> colorCodes;
    public boolean woSize;
    public boolean vid;
    public String v360;

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public int getPicsCnt() {
        return picsCnt;
    }

    public void setPicsCnt(int picsCnt) {
        this.picsCnt = picsCnt;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public String getPromoTxt() {
        return promoTxt;
    }

    public void setPromoTxt(String promoTxt) {
        this.promoTxt = promoTxt;
    }

    public List<Size> getSizes() {
        return sizes;
    }

    public void setSizes(List<Size> sizes) {
        this.sizes = sizes;
    }

    public List<Integer> getColorCodes() {
        return colorCodes;
    }

    public void setColorCodes(List<Integer> colorCodes) {
        this.colorCodes = colorCodes;
    }
}
