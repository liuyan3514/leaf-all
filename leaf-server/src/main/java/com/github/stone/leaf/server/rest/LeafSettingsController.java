package com.github.stone.leaf.server.rest;

import java.util.List;

import com.github.stone.leaf.server.Constants;
import com.github.stone.leaf.server.entity.LeafSettings;
import com.github.stone.leaf.server.enums.LeafMessage;
import com.github.stone.leaf.server.rest.result.LeafResult;
import com.github.stone.leaf.server.service.LeafSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * leaf settings controller
 *
 * @author stone
 */
@Controller
public class LeafSettingsController {

    @Autowired
    private LeafSettingsService leafSettingsService;

    @ResponseBody
    @RequestMapping(value = "/settings", method = RequestMethod.PUT)
    public LeafResult add(@RequestBody LeafSettings leafSettings) {
        leafSettingsService.addLeafSettings(leafSettings);
        return new LeafResult(leafSettings.getName(), LeafMessage.SUCCESS);
    }

    @ResponseBody
    @RequestMapping(value = "/settings/{leafName}", method = RequestMethod.GET)
    public LeafResult get(@PathVariable("leafName") String leafName) {
        if (Constants.REST_ALL_MATCH.equals(leafName)) {
            List<LeafSettings> data = leafSettingsService.getAll();
            return new LeafResult(data, LeafMessage.SUCCESS);
        }
        LeafSettings data = leafSettingsService.getSettings(leafName);
        return new LeafResult(data, LeafMessage.SUCCESS);
    }

}
