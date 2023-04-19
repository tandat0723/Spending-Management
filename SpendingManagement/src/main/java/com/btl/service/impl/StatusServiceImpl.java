/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.service.impl;

import com.btl.pojo.Status;
import com.btl.repository.StatusRepository;
import com.btl.service.StatusService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author phuan
 */
@Service
public class StatusServiceImpl implements StatusService{

    @Autowired
    private StatusRepository statusRepository;
    
    @Override
    public List<Status> getStatus() {
        return this.statusRepository.getStatus();
    }
    
}
