package org.abror.service.impl;

import java.util.Optional;
import org.abror.domain.Product;
import org.abror.repository.ProductRepository;
import org.abror.service.FileUploadService;
import org.abror.service.ProductService;
import org.abror.service.dto.ProductDTO;
import org.abror.service.mapper.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link org.abror.domain.Product}.
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;
    private final FileUploadService fileUploadService;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, FileUploadService fileUploadService, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.fileUploadService = fileUploadService;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        log.debug("Request to save Product : {}", productDTO);
        saveImageFile(productDTO);

        Product product = productMapper.toEntity(productDTO);
        product = productRepository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public ProductDTO update(ProductDTO productDTO) {
        log.debug("Request to update Product : {}", productDTO);
        saveImageFile(productDTO);

        Product product = productMapper.toEntity(productDTO);
        product = productRepository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public Optional<ProductDTO> partialUpdate(ProductDTO productDTO) {
        log.debug("Request to partially update Product : {}", productDTO);

        return productRepository
            .findById(productDTO.getId())
            .map(existingProduct -> {
                productMapper.partialUpdate(existingProduct, productDTO);
                if (productDTO.getImageFile() != null) {
                    saveImageFile(productDTO);
                }
                return existingProduct;
            })
            .map(productRepository::save)
            .map(productMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProductDTO> findOne(Long id) {
        log.debug("Request to get Product : {}", id);
        return productRepository.findById(id).map(productMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Product : {}", id);
        productRepository.deleteById(id);
    }

    private void saveImageFile(ProductDTO productDTO) {
        if (productDTO.getImageFile() != null && productDTO.getImageKey() != null) {
            fileUploadService.uploadFile(productDTO.getName(), productDTO.getImageFile());
        }
    }
}
