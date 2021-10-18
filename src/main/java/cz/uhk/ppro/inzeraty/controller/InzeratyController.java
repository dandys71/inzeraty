package cz.uhk.ppro.inzeraty.controller;

import cz.uhk.ppro.inzeraty.model.Inzerat;
import cz.uhk.ppro.inzeraty.sluzby.PametoveUlozisteInzeratu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.http.HttpResponse;
import java.util.List;

@Controller
public class InzeratyController {

    @Autowired
    private PametoveUlozisteInzeratu ulozisteInzeratu;

    @GetMapping("/")
    public String vratInzeraty(Model model){
        return vratInzeraty(model, "vsechny");
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String vratInzeraty(Model model, @RequestParam String kat){

        List<Inzerat> inzeraty;

        if(kat.isEmpty()  || kat.equals("vsechny")) {
            inzeraty = ulozisteInzeratu.getInzeraty();
        }else{
            inzeraty = ulozisteInzeratu.getInzeratyByKategorie(kat);
        }
        if(inzeraty.isEmpty()){
            model.addAttribute("chyba", "Není vložený žádný inzerát");
        }else {
            model.addAttribute("inzeraty", inzeraty);
        }

        model.addAttribute("kategorie", Inzerat.Kategorie.vratVsechnyKategorie());
        return "inzeraty";
    }

    @GetMapping("novy-inzerat")
    public ModelAndView novyInzerat(Model model){
        model.addAttribute("kategorie", Inzerat.Kategorie.vratVsechnyKategorie());
        return new ModelAndView("novy-inzerat", "inzerat", new Inzerat());
    }

    @RequestMapping(value = "/pridany-inzerat", method = RequestMethod.POST)
    public String pridejInzerat(Model model, @RequestParam String kategorie, @RequestParam String text, @RequestParam(required = false) BigDecimal cena){

        if(text.isEmpty()){
           text = "Bez popisu";
       }

       if(cena == null){
           cena = BigDecimal.valueOf(0);
       }

       Inzerat novyInzerat = new Inzerat(kategorie, text, cena);
       ulozisteInzeratu.pridej(novyInzerat);

       model.addAttribute("message", "Inzerát byl úspěšně přidán, heslo pro úpravu či smazání je: " + novyInzerat.getHesloProUpravu());


        return vratInzeraty(model);
    }

    @RequestMapping(value = "upraveny-inzerat", method = RequestMethod.POST)
    public String upravitInzerat(Model model,@RequestParam int id, @RequestParam String kategorie, @RequestParam String text, @RequestParam (required = false) BigDecimal cena, @RequestParam String heslo){
        Inzerat i = ulozisteInzeratu.getById(id);
        if(i.getHesloProUpravu().equals(heslo)){
            if(text.isEmpty())
                text = "Bez popisu";
            if(cena == null)
                cena = BigDecimal.valueOf(0);

            i.setKategorie(kategorie);
            i.setText(text);
            i.setCena(cena);

            ulozisteInzeratu.upravInzerat(i);

            model.addAttribute("message", "Inzerát byl úspěšně upraven!");
        }else{
            model.addAttribute("message", "Heslo pro úpravu se neshoduje!");
        }

        model.addAttribute("i", i);
        model.addAttribute("kategorie", Inzerat.Kategorie.vratVsechnyKategorie());
        return "upravit-inzerat";
    }

    @RequestMapping(value = "upravit-inzerat", method = RequestMethod.POST)
    public String upravitInzerat(Model model, @RequestParam int id){
        Inzerat i = ulozisteInzeratu.getById(id);
        model.addAttribute("i", i);
        model.addAttribute("kategorie", Inzerat.Kategorie.vratVsechnyKategorie());
        return "upravit-inzerat";
    }

    @RequestMapping(value = "smazat-inzerat", method = RequestMethod.POST)
    public String smazatInzerat(Model model, @RequestParam int id){
        model.addAttribute("i", ulozisteInzeratu.getById(id));
        return "smazat-inzerat";
    }

    @RequestMapping(value = "smazany-inzerat", method = RequestMethod.POST)
    public String smazatInzerat(Model model, @RequestParam int id,  @RequestParam String heslo){
        Inzerat i = ulozisteInzeratu.getById(id);

        if(i.getHesloProUpravu().equals(heslo)){
            ulozisteInzeratu.odstran(i);
            model.addAttribute("message", "Inzerát byl úspěšně odstraněn!"); //nemá smysl, nevypíše se
            return "redirect:/";
        }else{
            model.addAttribute("message","Heslo pro odstranění se neshoduje!");
        }

        model.addAttribute("i", ulozisteInzeratu.getById(id));
        return "smazat-inzerat";
    }


}
