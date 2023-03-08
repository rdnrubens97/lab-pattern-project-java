package dio.labpatternprojectjava.service;

import dio.labpatternprojectjava.model.Adress;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Service
@FeignClient(name = "viazipcode", url = "https://viazipcode.com.br/ws")
public interface ViaZipcodeService {
    @RequestMapping(method = RequestMethod.GET, value = "/{cep}/json/")
    Adress consultZipcode(@PathVariable("zipCode") String zipCode);

}
