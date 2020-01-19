package com.wildcodeschool.wildandwizard.controller;


import com.wildcodeschool.wildandwizard.entity.Wizard;
//new: WizardDao:
import com.wildcodeschool.wildandwizard.repository.WizardDao;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import com.wildcodeschool.wildandwizard.repository.WizardRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WizardController {
	

	//old/delete/change: -->    private WizardRepository repository = new WizardRepository();
	
	
// New with Dependency Injection =========================
	@Autowired
    private WizardDao repository;
//	------------------> End: New with Dependency Injection ========================= 

	
/* additional explanation:======================================================

	Note that the WizardRepository instance is/was currently tightly coupled: It's created during construction
	of a WizardController instance. Let's perform some changes to get ready for loose coupling.
	
	We assume there's an WizardDao interface that describes a contract and that WizardRepository implements
	this interface.
	
	This allows us to use the interface type in the declaration of the repository field:
	" private WizardDao repository = new WizardRepository(); ".
	
--> Next step: Now, we're going to ask Spring to provide us with an instance of some implementation of the
    interface WizardDao. This is done by adding the @Autowired annotation:
    
    " ...
    @Autowired
    private WizardDao repository;

    // code omitted..."
	
================================================================================
*/
	

    @GetMapping("/wizards")
    public String getAll(Model model) {

        model.addAttribute("wizards", repository.findAll());

        return "wizards";
    }
    
    
    @GetMapping("/wizard")
    public String getWizard(Model model,
                            @RequestParam(required = false) Long id) {

        Wizard wizard = new Wizard();
        if (id != null) {
            wizard = repository.findById(id);
        }
        model.addAttribute("wizard", wizard);

        return "wizard";
    }
    
    @PostMapping("/wizard")
    public String postWizard(@ModelAttribute Wizard wizard) {

        if (wizard.getId() != null) {
            repository.update(wizard);
        } else {
            repository.save(wizard);
        }
        return "redirect:/wizards";
    }
    
    

    @GetMapping("/wizard/delete")
    public String deleteWizard(@RequestParam Long id) {

        repository.deleteById(id);

        return "redirect:/wizards";
    }

    @GetMapping("/")
    public String index() {

        return "redirect:/wizards";
    }
}




