package br.com.vitorcamposdsilva.todolist.util;

import org.springframework.beans.*;


import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

public class Util {

    public static void copiarPropriedadeNula(Object object, Object target){
        BeanUtils.copyProperties(object,target, getCamposNulos(object));
    }

    public static String[] getCamposNulos(Object object){
        final BeanWrapper src = new BeanWrapperImpl(object);

        PropertyDescriptor[]  pds = src.getPropertyDescriptors();

        Set<String> nomeEntidade = new HashSet<>();

        for (PropertyDescriptor pd:pds){
            Object valorCampo = src.getPropertyValue(pd.getName());
            if (valorCampo == null){
                nomeEntidade.add(pd.getName());
            }
        }

        String[] resultado = new String[nomeEntidade.size()];

        return nomeEntidade.toArray(resultado);
    }
}
