package com.example.Pawnectados.services;

import com.example.Pawnectados.repositorios.DonacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonacionService {

    @Autowired
    private DonacionRepository donacionRepository;

    public long contarDonaciones() {
        return donacionRepository.count();
    }
}