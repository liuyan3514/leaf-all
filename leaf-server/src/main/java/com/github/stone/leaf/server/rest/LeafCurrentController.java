package com.github.stone.leaf.server.rest;

import com.github.stone.leaf.server.entity.LeafSettings;
import com.github.stone.leaf.server.enums.LeafMessage;
import com.github.stone.leaf.server.rest.result.LeafResult;
import com.github.stone.leaf.server.service.LeafCurrentServiceFactory;
import com.github.stone.leaf.server.service.LeafSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * leaf current controller
 *
 * @author stone
 */
@Controller
public class LeafCurrentController {

    @Autowired
    private LeafSettingsService       settingsService;

    @Autowired
    private LeafCurrentServiceFactory currentServiceFactory;

    @ResponseBody
    @RequestMapping(value = "/current/value/{leafName}", method = RequestMethod.GET)
    public Object getCurrent(@PathVariable("leafName") String leafName) {
        LeafSettings leafSettings = settingsService.getSettings(leafName);
        if (leafSettings == null) {
            return new LeafResult(LeafMessage.NOT_FOUND_LEAF);
        }
        Long current = currentServiceFactory.getCurrentService(leafSettings)
            .getLeafCurrent(leafSettings);
        return new LeafResult(current, LeafMessage.SUCCESS);
    }

}
