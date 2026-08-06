package com.ecommerce.backend.config;

import com.ecommerce.backend.model.Brand;
import com.ecommerce.backend.model.Category;
import com.ecommerce.backend.model.Product;
import com.ecommerce.backend.repository.BrandRepository;
import com.ecommerce.backend.repository.CategoryRepository;
import com.ecommerce.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
@Transactional
public class DataInitializer implements CommandLineRunner {

    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    public void run(String... args) {

        createBrands();

        createCategories();

        createProducts();

    }

    private void createBrands() {

        if (brandRepository.count() > 0) {
            return;
        }

        brandRepository.save(
                Brand.builder()
                        .name("Nike")
                        .country("USA")
                        .description("Marca líder en calzado deportivo.")
                        .active(true)
                        .build()
        );

        brandRepository.save(
                Brand.builder()
                        .name("Adidas")
                        .country("Alemania")
                        .description("Marca alemana especializada en deporte.")
                        .active(true)
                        .build()
        );

        brandRepository.save(
                Brand.builder()
                        .name("New Balance")
                        .country("USA")
                        .description("Marca reconocida por su comodidad.")
                        .active(true)
                        .build()
        );

        brandRepository.save(
                Brand.builder()
                        .name("Puma")
                        .country("Alemania")
                        .description("Marca deportiva internacional.")
                        .active(true)
                        .build()
        );

        System.out.println("✅ Marcas creadas");

    }

    private void createCategories() {

        if (categoryRepository.count() > 0) {
            return;
        }

        categoryRepository.save(
                Category.builder()
                        .name("Running")
                        .description("Zapatillas para correr.")
                        .active(true)
                        .build()
        );

        categoryRepository.save(
                Category.builder()
                        .name("Lifestyle")
                        .description("Zapatillas para uso diario.")
                        .active(true)
                        .build()
        );

        categoryRepository.save(
                Category.builder()
                        .name("Training")
                        .description("Entrenamiento.")
                        .active(true)
                        .build()
        );

        categoryRepository.save(
                Category.builder()
                        .name("Basketball")
                        .description("Baloncesto.")
                        .active(true)
                        .build()
        );

        System.out.println("✅ Categorías creadas");

    }

    private void createProducts() {

        if (productRepository.count() > 0) {
            return;
        }

        Brand nike = brandRepository.findByNameIgnoreCase("Nike").orElseThrow();
        Brand adidas = brandRepository.findByNameIgnoreCase("Adidas").orElseThrow();
        Brand newBalance = brandRepository.findByNameIgnoreCase("New Balance").orElseThrow();
        Brand puma = brandRepository.findByNameIgnoreCase("Puma").orElseThrow();

        Category running = categoryRepository.findByNameIgnoreCase("Running").orElseThrow();
        Category lifestyle = categoryRepository.findByNameIgnoreCase("Lifestyle").orElseThrow();



        createProduct(
                "Nike Air Max 90",
                "Zapatilla deportiva icónica.",
                "nike-air-max-90.png",
                new BigDecimal("179.99"),
                50,
                nike,
                running
        );

        createProduct(
                "Adidas Samba",
                "Uno de los modelos más vendidos.",
                "adidas-samba.png",
                new BigDecimal("129.99"),
                35,
                adidas,
                lifestyle
        );

        createProduct(
                "New Balance 574",
                "Comodidad para el día a día.",
                "new-balance-574.png",
                new BigDecimal("139.99"),
                40,
                newBalance,
                lifestyle
        );

        createProduct(
                "Puma RS-X",
                "Diseño moderno y deportivo.",
                "puma-rs-x.png",
                new BigDecimal("149.99"),
                25,
                puma,
                running
        );

        createProduct(
                "Nike Air Force 1",
                "Un clásico urbano con diseño atemporal.",
                "nike-air-force-1.png",
                new BigDecimal("159.99"),
                30,
                nike,
                lifestyle
        );

        createProduct(
                "Nike Pegasus 41",
                "Ligera, cómoda y perfecta para entrenamientos diarios.",
                "nike-pegasus-41.png",
                new BigDecimal("169.99"),
                28,
                nike,
                running
        );

        createProduct(
                "Adidas Ultraboost Light",
                "Máximo retorno de energía para corredores.",
                "adidas-ultraboost-light.png",
                new BigDecimal("189.99"),
                25,
                adidas,
                running
        );

        createProduct(
                "Adidas Campus 00s",
                "Diseño retro inspirado en los años 2000.",
                "adidas-campus-00s.png",
                new BigDecimal("139.99"),
                32,
                adidas,
                lifestyle
        );

        createProduct(
                "New Balance 530",
                "Estilo clásico con gran comodidad para el día a día.",
                "new-balance-530.png",
                new BigDecimal("149.99"),
                22,
                newBalance,
                lifestyle
        );

        createProduct(
                "New Balance 9060",
                "Diseño moderno con amortiguación premium.",
                "new-balance-9060.png",
                new BigDecimal("199.99"),
                18,
                newBalance,
                lifestyle
        );

        createProduct(
                "Puma Suede Classic",
                "Un clásico de Puma con estilo atemporal.",
                "puma-suede-classic.png",
                new BigDecimal("119.99"),
                40,
                puma,
                lifestyle
        );

        createProduct(
                "Puma Velocity Nitro",
                "Zapatilla de running ligera y reactiva.",
                "puma-velocity-nitro.png",
                new BigDecimal("169.99"),
                26,
                puma,
                running
        );

        System.out.println("✅ Productos creados");


    }

    private void createProduct(
            String name,
            String description,
            String imageUrl,
            BigDecimal price,
            Integer stock,
            Brand brand,
            Category category
    ) {

        productRepository.save(

                Product.builder()
                        .name(name)
                        .description(description)
                        .price(price)
                        .stock(stock)
                        .imageUrl(imageUrl)
                        .brand(brand)
                        .category(category)
                        .active(true)
                        .build()

        );

    }

}