package mz.co.insystems.mobicare.common;

import mz.co.insystems.mobicare.model.contacto.Contacto;
import mz.co.insystems.mobicare.model.endereco.Endereco;
import mz.co.insystems.mobicare.model.farmacia.Farmacia;

/**
 * Created by Voloide Tamele on 4/16/2018.
 */

public interface SearchbleObject {

    String getDescricao();
    long getId();
    Endereco getEndereco();
    Contacto getContacto();
    Farmacia getFarmacia();
    int getDisponibilidade();
}
