package repositories;

import entities.Product;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ProductRepository {
    @Inject
    EntityManager em;

    @Transactional
    public void createdProduct(Product p){
       em.persist(p);
       em.flush();
    }

    @Transactional
    public void deleteProduct(Product p){
        em.remove(em.contains(p) ? p : em.merge(p));
    }

    @Transactional
    public List<Product> listProduct(){
        return em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }
    @Transactional
    public Product findProduct(Long Id){
        return em.find(Product.class, Id);
    }

    @Transactional
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = findProduct(id);
        if (existingProduct == null) {
            return null;
        }
        existingProduct.setCode(updatedProduct.getCode());
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());

        return em.merge(existingProduct);
    }
}
