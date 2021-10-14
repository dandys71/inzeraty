package cz.uhk.ppro.inzeraty.controller;

import cz.uhk.ppro.inzeraty.model.Inzerat;
import cz.uhk.ppro.inzeraty.sluzby.PametoveUlozisteInzeratu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class InzeratyController {

    @Autowired
    private PametoveUlozisteInzeratu ulozisteInzeratu;

    @GetMapping("inzeraty")
    @ResponseBody
    public String vratInzeraty(){
        StringBuilder result = new StringBuilder();

        for(Inzerat i: ulozisteInzeratu.getInzeraty()){
            result.append(i.toString());
        }
        if(result.toString().isEmpty()){
            result = new StringBuilder("Není vložený žádný inzerát");
        }
        return result.toString();
    }

    @GetMapping("novy-inzerat")
    public String novyInzerat(){
        return "novy-inzerat";
    }


}
