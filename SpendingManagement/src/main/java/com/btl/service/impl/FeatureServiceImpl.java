/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.service.impl;

import com.btl.pojo.Feature;
import com.btl.repository.FeatureRepository;
import com.btl.service.FeatureService;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author trant
 */
@Service
public class FeatureServiceImpl implements FeatureService {
    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private FeatureRepository featureRepository;

    @Override
    public List<Feature> getFeatures(Map<String, String> params) {
        return this.featureRepository.getFeatures(params);
    }

    public Feature getFeatureById(int id) {
        return this.featureRepository.getFeatureById(id);
    }

}
