package com.educational.educational.dao;

import com.educational.educational.beans.MaterialRecentBean;
import com.educational.educational.beans.PercentageBean;
import com.educational.educational.models.Downloads;
import com.educational.educational.models.Materials;

import java.util.List;

public interface MaterialDao {
    List<Materials> getMaterials(Integer userID, Integer courseID);

    Materials createMaterial(Materials material, Integer userID);

    boolean deleteMaterial(Integer courseID, Integer materialID, Integer userID);

    List<MaterialRecentBean> getMaterialsRecent(Integer userID);

    boolean downloadMaterial(Downloads newDownload);

    PercentageBean downloadPercentageMaterial(Integer userID, Integer courseID);
}
