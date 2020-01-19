package com.wildcodeschool.wildandwizard.repository;

import com.wildcodeschool.wildandwizard.entity.Wizard;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// new import: Autowire AND Repository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


//new: "@Repository==============================================================>>>>>>

@Repository

//new-Ende ============================================================================



/* additional explanation:======================================================

... it is necessary to annotate the WizardRepository class so that Spring knows that it should consider it
for injection.
The basic annotation here is @Component. However, Spring defines a number of other annotations that can be
used as well, for example: @Service or @Repository.

In a nutshell, these annotations are used to detail the role of the class, here it seems appropriate to use
@Repository:
================================================================================
*/


public class WizardRepository implements WizardDao {

  private static List<Wizard> wizards = new ArrayList<>(
          Arrays.asList(
                  new Wizard(1L, "Harry", "Potter", new Date(80, 6, 31), "London", "", false)
          )
  );
    
    
    @Override
    public Wizard save(Wizard wizard) {

        wizard.setId((long) (wizards.size() + 1));
        wizards.add(wizard);
        return wizard;
    }
    
    @Override
    public Wizard findById(Long id) {

        for (Wizard wizard : wizards) {
            if (wizard.getId().equals(id)) {
                return wizard;
            }
        }
        return null;
    }
    
    @Override
    public List<Wizard> findAll() {

        return wizards;
    }
    
    @Override
    public Wizard update(Wizard wizard) {

        for (Wizard update : wizards) {
            if (update.getId().equals(wizard.getId())) {
                update.setFirstName(wizard.getFirstName());
                update.setLastName(wizard.getLastName());
                update.setBirthday(wizard.getBirthday());
                update.setBirthPlace(wizard.getBirthPlace());
                update.setBiography(wizard.getBiography());
                update.setMuggle(wizard.isMuggle());

                return update;
            }
        }
        return null;
    }
    

    @Override
    public void deleteById(Long id) {

        for (Wizard wizard : wizards) {
            if (wizard.getId().equals(id)) {
                wizards.remove(wizard);
                break;
            }
        }
    }
}


 

