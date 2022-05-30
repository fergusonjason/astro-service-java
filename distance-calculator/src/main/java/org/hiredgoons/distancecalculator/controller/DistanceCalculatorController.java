package org.hiredgoons.distancecalculator.controller;

import org.apache.commons.collections4.CollectionUtils;
import org.hiredgoons.common.dto.Positional;
import org.hiredgoons.distancecalculator.service.DistanceService;
import org.hiredgoons.distancecalculator.util.DistanceUtil;
import org.springframework.aop.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/")
public class DistanceCalculatorController {

    @Autowired
    private DistanceService service;

    @GetMapping
    public @ResponseBody ResponseEntity<BigDecimal> computeDistance(PositionalImpl object1, PositionalImpl object2) {

        BigDecimal result = DistanceUtil.computeDistanceBetweenObjects(object1, object2);
        //BigDecimal result = service.computeDistance(object1, object2);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public static class PositionalImpl implements Positional {

        private String rightAscension;
        private String declination;
        private BigDecimal parallax;

        @Override
        public String getRightAscension() {
            return rightAscension;
        }

        public void setRightAscension(String rightAscension) {
            this.rightAscension = rightAscension;
        }

        @Override
        public String getDeclination() {
            return declination;
        }

        public void setDeclination(String declination) {
            this.declination = declination;
        }

        @Override
        public BigDecimal getParallax() {
            return parallax;
        }

        public void setParallax(BigDecimal parallax) {
            this.parallax = parallax;
        }
    }
//    public static class DistanceQuery implements Serializable {
//
//        private Positional object1;
//        private Positional object2;
//
//        public Positional getObject1() {
//            return object1;
//        }
//
//        public void setObject1(Positional object1) {
//            this.object1 = object1;
//        }
//
//        public Positional getObject2() {
//            return object2;
//        }
//
//        public void setObject2(Positional object2) {
//            this.object2 = object2;
//        }
//    }
}
