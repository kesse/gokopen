package se.gokopen.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import se.gokopen.persistence.entity.StationEntity;
import se.gokopen.persistence.exception.StationNotFoundException;
import se.gokopen.service.StationService;

@RequestMapping("/admin/station")
@Controller
public class StationController {

    @Autowired
    private StationService stationService;

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(StationEntity station, BindingResult errors, HttpServletRequest request, HttpServletResponse response) {
        stationService.saveStation(station);

        //Lista på stationer
        List<StationEntity> stations = stationService.getAllStations();
        return new ModelAndView("stationlist", "stations", stations);
    }

    @RequestMapping(value = "/newstation", method = RequestMethod.GET)
    public ModelAndView newStation() {
        StationEntity station = new StationEntity();
        ModelMap map = new ModelMap();
        map.put("station", station);
        return new ModelAndView("station", map);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listAllStations() {
        //Return to list of existing patrols
        List<StationEntity> stations = stationService.getAllStations();
        return new ModelAndView("stationlist", "stations", stations);
    }

    //Edit station
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editStation(@PathVariable String id, HttpServletRequest request) {
        StationEntity station = null;
        try {
            station = stationService.getStationById(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (StationNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ModelAndView("station", "station", station);
    }

    //Delete station
    @RequestMapping(value = "/delete/{id}")
    public ModelAndView deleteStation(@PathVariable String id, HttpServletRequest request) {
        try {
            stationService.deleteStationById(Integer.parseInt(id));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            request.setAttribute("errormsg", "Det gick inte att ta bort kontrollen just nu.");
            List<StationEntity> stations = stationService.getAllStations();
            return new ModelAndView("stationlist", "stations", stations);
        }

        //Return to list of existing patrols
        List<StationEntity> stations = stationService.getAllStations();
        return new ModelAndView("stationlist", "stations", stations);
    }

}
