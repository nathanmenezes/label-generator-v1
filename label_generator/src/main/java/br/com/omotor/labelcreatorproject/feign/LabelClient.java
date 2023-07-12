package br.com.omotor.labelcreatorproject.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URI;
import java.util.HashMap;

@FeignClient(name = "label", url = "http://localhost:8080")
public interface LabelClient {

    @GetMapping
    HashMap<String, String> fetchLabels(URI baseurl);

}
