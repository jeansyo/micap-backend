package com.educational.educational.controllers;

import com.educational.educational.beans.*;
import com.educational.educational.dao.MaterialDao;
import com.educational.educational.models.Materials;
import com.educational.educational.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

@CrossOrigin(origins = "*")
@RestController
public class MaterialController {

    @Autowired
    private MaterialDao MaterialDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/materials/{courseID}", method = RequestMethod.GET)
    public ResponseEntity<MaterialsResponseBean> getMaterials(@RequestHeader( value = "X-token" ) String token, @PathVariable Integer courseID ) {

        ResponseBean responseBean = new ResponseBean();
        responseBean.setDate(new Date().toString());

        MaterialsResponseBean materialsResponseBean = new MaterialsResponseBean();

        String userID = jwtUtil.getKey(token);

        if(userID == null) {

            responseBean.setCodeError("401");
            responseBean.setMsgError("Token no valido");
            materialsResponseBean.setAPI(responseBean);

            return new ResponseEntity<MaterialsResponseBean>(materialsResponseBean, HttpStatus.FORBIDDEN);

        }

        List<Materials> result = MaterialDao.getMaterials(parseInt(userID), courseID);

        if(result == null) {
            responseBean.setCodeError("404");
            responseBean.setMsgError("Materiales no encontrados");
            materialsResponseBean.setAPI(responseBean);
            return new ResponseEntity<MaterialsResponseBean>(materialsResponseBean, HttpStatus.NOT_FOUND);
        }

        ArrayList<MaterialBean> resultsMaterial = new ArrayList<MaterialBean>();

        result.forEach(material -> {
            MaterialBean materialBean = new MaterialBean();
            materialBean.setLink(material.getLink());
            materialBean.setName(material.getName());
            materialBean.setId(material.getId());
            materialBean.setType(material.getType());
            materialBean.setDate(material.getDate());
            materialBean.setFilename(material.getFilename());

            resultsMaterial.add(materialBean);

        });

        responseBean.setCodeError("200");
        responseBean.setMsgError("Materiales encontrados");

        materialsResponseBean.setAPI(responseBean);
        materialsResponseBean.setResult(resultsMaterial);

        return new ResponseEntity<MaterialsResponseBean>(materialsResponseBean, HttpStatus.OK);

    }

    @RequestMapping(value = "api/materials/{courseID}", method = RequestMethod.POST)
    public ResponseEntity<MaterialResponseBean> createMaterial(@RequestHeader( value = "X-token" ) String token, @PathVariable Integer courseID, @RequestBody Materials material ) {

        ResponseBean responseBean = new ResponseBean();
        responseBean.setDate(new Date().toString());

        MaterialResponseBean materialResponseBean = new MaterialResponseBean();

        String userID = jwtUtil.getKey(token);

        if(userID == null) {

            responseBean.setCodeError("401");
            responseBean.setMsgError("Token no valido");
            materialResponseBean.setAPI(responseBean);

            return new ResponseEntity<MaterialResponseBean>(materialResponseBean, HttpStatus.FORBIDDEN);

        }

        material.setCourse(courseID);

        Materials materialCreated = MaterialDao.createMaterial(material, parseInt(userID));

        if(materialCreated == null) {
            responseBean.setCodeError("403");
            responseBean.setMsgError("No autorizado");

            materialResponseBean.setAPI(responseBean);
            return new ResponseEntity<MaterialResponseBean>(materialResponseBean, HttpStatus.UNAUTHORIZED);
        }

        MaterialBean materialBean = new MaterialBean();
        materialBean.setDate(materialCreated.getDate());
        materialBean.setName(materialCreated.getName());
        materialBean.setType(materialCreated.getType());
        materialBean.setLink(materialCreated.getLink());
        materialBean.setId(materialCreated.getId());
        materialBean.setFilename(materialCreated.getFilename());

        responseBean.setCodeError("201");
        responseBean.setMsgError("Material creado exitosamente");
        materialResponseBean.setResult(materialBean);
        materialResponseBean.setAPI(responseBean);


        return new ResponseEntity<MaterialResponseBean>(materialResponseBean, HttpStatus.CREATED);

    }

    @RequestMapping(value = "api/materials/{courseID}/{materialID}", method = RequestMethod.DELETE)
    public ResponseEntity<MaterialsResponseBean> deleteMaterial(@RequestHeader( value = "X-token" ) String token, @PathVariable Integer courseID, @PathVariable Integer materialID ) {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setDate(new Date().toString());

        MaterialsResponseBean materialsResponseBean = new MaterialsResponseBean();

        String userID = jwtUtil.getKey(token);

        if(userID == null) {

            responseBean.setCodeError("401");
            responseBean.setMsgError("Token no valido");
            materialsResponseBean.setAPI(responseBean);

            return new ResponseEntity<MaterialsResponseBean>(materialsResponseBean, HttpStatus.FORBIDDEN);

        }

        boolean deletedMaterial = MaterialDao.deleteMaterial(courseID, materialID, parseInt(userID));

        if( !deletedMaterial ) {
            responseBean.setCodeError("403");
            responseBean.setMsgError("No autorizado");
            materialsResponseBean.setAPI(responseBean);

            return new ResponseEntity<MaterialsResponseBean>(materialsResponseBean, HttpStatus.UNAUTHORIZED);
        }

        responseBean.setCodeError("200");
        responseBean.setMsgError("Material eliminado exitosamente");
        materialsResponseBean.setAPI(responseBean);

        return new ResponseEntity<MaterialsResponseBean>(materialsResponseBean, HttpStatus.OK);

    }


    @RequestMapping(value = "api/materials/recent", method = RequestMethod.GET)
    public ResponseEntity<MaterialsRecentResponseBean> getMaterialsRecentByCourse(@RequestHeader( value = "X-token" ) String token ) {

        MaterialsRecentResponseBean materialsRecentResponseBean = new MaterialsRecentResponseBean();

        ResponseBean responseBean = new ResponseBean();
        responseBean.setDate(new Date().toString());

        String userID = jwtUtil.getKey(token);
        if(userID == null) {
            responseBean.setCodeError("401");
            responseBean.setMsgError("Token no valido");

            materialsRecentResponseBean.setAPI(responseBean);

            return new ResponseEntity<MaterialsRecentResponseBean>(materialsRecentResponseBean, HttpStatus.FORBIDDEN);
        }

        List<MaterialRecentBean> resultMaterials = MaterialDao.getMaterialsRecent(parseInt(userID));

        if( resultMaterials == null ) {
            responseBean.setCodeError("409");
            responseBean.setMsgError("Datos erroneos");

            materialsRecentResponseBean.setAPI(responseBean);

            return new ResponseEntity<MaterialsRecentResponseBean>(materialsRecentResponseBean, HttpStatus.CONFLICT);
        }

        responseBean.setCodeError("200");
        responseBean.setMsgError("Materiales recientes");

        materialsRecentResponseBean.setAPI(responseBean);
        materialsRecentResponseBean.setResult(resultMaterials);

        return new ResponseEntity<MaterialsRecentResponseBean>(materialsRecentResponseBean, HttpStatus.OK);

    }


    }
