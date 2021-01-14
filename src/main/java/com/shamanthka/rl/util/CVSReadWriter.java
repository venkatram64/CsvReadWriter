package com.shamanthka.rl.util;

import com.shamanthka.rl.model.Item;
import com.shamanthka.rl.model.Product;
import com.shamanthka.rl.service.ItemService;
import com.shamanthka.rl.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
public class CVSReadWriter {

    @Autowired
    private ProductService productService;

    @Autowired
    private ItemService itemService;

    public void readAndWrite(){
        try {
            FileReader reader = new FileReader("products.csv");
            BufferedReader br = new BufferedReader(reader);
            String str = "";
            Product product = null;
            Item item = null;

            while((str = br.readLine()) != null){
                if(str.contains("product name")){
                    continue;
                }
                String[] line = str.split(",");
                String prodName = line[0];
                String modelNumber = line[1];

                String qty = line[2];
                int quantity = Integer.parseInt(qty.trim());

                String unitPrice = line[3];
                float up = Float.parseFloat(unitPrice.trim());

                String brandId = line[4];
                int brdId = Integer.parseInt(brandId.trim());
                System.out.println(str);

                product = new Product();
                product.setName(prodName);
                product.setQuantity(quantity);
                product.setModelNumber(modelNumber.trim());
                product.setBrandId(brdId);
                Product p = productService.save(product);
                item = new Item();
                item.setPrice(up);
                item.setProduct(p);
                itemService.save(item);
            }
            br.close();
            reader.close();
        }catch (IOException io){
            io.printStackTrace();
        }
    }
}
