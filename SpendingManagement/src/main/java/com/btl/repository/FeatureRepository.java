/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.repository;

import com.btl.pojo.Feature;
import java.util.List;
import java.util.Map;

/**
 * @author trant
 */
public interface FeatureRepository {
    List<Feature> getFeatures(Map<String, String> params);
    Feature getFeatureById(int id);
}
