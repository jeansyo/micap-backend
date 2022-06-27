package com.educational.educational.controllers;

import com.educational.educational.beans.CoursesResponseBean;
import com.educational.educational.beans.MaterialsResponseBean;
import com.educational.educational.beans.ResponseBean;
import com.educational.educational.dao.MaterialDao;
import com.educational.educational.models.Materials;
import com.educational.educational.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

@RestController
public class MaterialController {

    @Autowired
    private MaterialDao MaterialDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/materials/{courseID}", method = RequestMethod.GET)
    public ResponseEntity<MaterialsResponseBean> getCoursesById(@RequestHeader( value = "X-token" ) String token, @PathVariable Integer courseID ) {

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

        responseBean.setCodeError("200");
        responseBean.setMsgError("Materiales encontrados");

        materialsResponseBean.setAPI(responseBean);
        materialsResponseBean.setResult(result);

        return new ResponseEntity<MaterialsResponseBean>(materialsResponseBean, HttpStatus.OK);

    }

    @RequestMapping(value = "api/materials/{courseID}", method = RequestMethod.POST)
    public ResponseEntity<MaterialsResponseBean> createCoursesById(@RequestHeader( value = "X-token" ) String token, @PathVariable Integer courseID, @RequestBody Materials material ) {

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

        material.setCourse(courseID);

        boolean materialCreated = MaterialDao.createMaterial(material, parseInt(userID));
        if(!materialCreated) {
            responseBean.setCodeError("403");
            responseBean.setMsgError("No autorizado");

            materialsResponseBean.setAPI(responseBean);
            return new ResponseEntity<MaterialsResponseBean>(materialsResponseBean, HttpStatus.UNAUTHORIZED);
        }

        responseBean.setCodeError("201");
        responseBean.setMsgError("Material creado exitosamente");

        materialsResponseBean.setAPI(responseBean);
        return new ResponseEntity<MaterialsResponseBean>(materialsResponseBean, HttpStatus.CREATED);

    }

    @RequestMapping(value = "api/materials/{courseID}/{materialID}", method = RequestMethod.DELETE)
    public ResponseEntity<MaterialsResponseBean> getCoursesById(@RequestHeader( value = "X-token" ) String token, @PathVariable Integer courseID, @PathVariable Integer materialID ) {
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


    @RequestMapping(value = "api/materials/{courseID}/recent", method = RequestMethod.GET)
    public ResponseEntity<MaterialsResponseBean> getMaterialsRecentByCourse(@RequestHeader( value = "X-token" ) String token, @PathVariable Integer courseID ) {

        MaterialsResponseBean materialsResponseBean = new MaterialsResponseBean();

        ResponseBean responseBean = new ResponseBean();
        responseBean.setDate(new Date().toString());

        String userID = jwtUtil.getKey(token);
        if(userID == null) {
            responseBean.setCodeError("401");
            responseBean.setMsgError("Token no valido");

            materialsResponseBean.setAPI(responseBean);

            return new ResponseEntity<MaterialsResponseBean>(materialsResponseBean, HttpStatus.FORBIDDEN);
        }

        List<Materials> resultMaterials = MaterialDao.getMaterialsRecent(parseInt(userID));

        if( resultMaterials == null ) {
            responseBean.setCodeError("409");
            responseBean.setMsgError("Datos erroneos");

            materialsResponseBean.setAPI(responseBean);

            return new ResponseEntity<MaterialsResponseBean>(materialsResponseBean, HttpStatus.CONFLICT);
        }

        responseBean.setCodeError("200");
        responseBean.setMsgError("Materiales recientes");

        materialsResponseBean.setAPI(responseBean);
        materialsResponseBean.setResult(resultMaterials);

        return new ResponseEntity<MaterialsResponseBean>(materialsResponseBean, HttpStatus.OK);

    }


    }
