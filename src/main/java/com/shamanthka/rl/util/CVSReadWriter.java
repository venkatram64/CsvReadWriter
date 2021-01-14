package com.shamanthka.rl.util;

import com.shamanthka.rl.model.Item;
import com.shamanthka.rl.model.Product;
import com.shamanthka.rl.model.ProductVO;
import com.shamanthka.rl.service.ItemService;
import com.shamanthka.rl.service.ProductPublisher;
import com.shamanthka.rl.service.ProductService;
import com.shamanthka.rl.service.ProductSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class CVSReadWriter {

    @Autowired
    private ProductService productService;

    @Autowired
    private ItemService itemService;

    //@Autowired
    private ProductPublisher publisher;

    //@Autowired
    private ProductSubscriber subscriber;

    @Autowired
    public void setSubscriber(ProductSubscriber subscriber) {
        this.subscriber = subscriber;
    }

    @Autowired
    public void setPublisher(ProductPublisher publisher){
        this.publisher = publisher;
    }

    public void readAndWrite(){
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = loader.getResourceAsStream("./products.csv");
            InputStreamReader reader = new InputStreamReader(inputStream);
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

                ProductVO productVO = new ProductVO();
                productVO.setId(p.getId());
                productVO.setName(p.getName());
                productVO.setQuantity(p.getQuantity());
                productVO.setModelNumber(p.getModelNumber());
                productVO.setBrandId(p.getBrandId());

                //publishing the product information into kafka topic
                publisher.publishProduct(productVO);
            }
            br.close();
            reader.close();
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    public void sendEmails(){
        System.out.println("I am printing...");
        subscriber.consume();
    }

    public void readAndWrite2(){
        try {
            //FileReader reader = new FileReader("products.csv");
            InputStream inputStream = this.getClass().getResourceAsStream("products.csv");
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
