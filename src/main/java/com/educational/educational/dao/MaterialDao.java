package com.educational.educational.dao;

import com.educational.educational.models.Materials;

import java.util.List;

public interface MaterialDao {
    List<Materials> getMaterials(Integer userID, Integer courseID);

    boolean createMaterial(Materials material, Integer userID);

    boolean deleteMaterial(Integer courseID, Integer materialID, Integer userID);

    List<Materials> getMaterialsRecent(Integer userID);
}
