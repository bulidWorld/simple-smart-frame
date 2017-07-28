package org.zwx.service.cfg;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

/**
 * Created by Administrator on 2017/7/24.
 */
public class ReportSupport {

    private static ReportParamLst paramLst;

    static {

        try {
            //TODO FILE NAME
            paramLst = new ObjectMapper().readValue(new File(""), ReportParamLst.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static ReportParam getParmByID(String ID){
        Optional<ReportParam> opt = paramLst.getLst().stream()
                                                .filter((param) -> param.getID().equals(ID))
                                                .findFirst();
        return opt.get();
    }




    private class ReportParamLst{
        private List<ReportParam> lst;

        public List<ReportParam> getLst() {
            return lst;
        }

        public void setLst(List<ReportParam> lst) {
            this.lst = lst;
        }
    }
}
