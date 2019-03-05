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

import se.gokopen.persistence.entity.TrackEntity;
import se.gokopen.persistence.exception.TrackNotFoundException;
import se.gokopen.service.TrackService;

@RequestMapping("/admin/track")
@Controller
public class TrackController {

    @Autowired
    private TrackService trackService;

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(TrackEntity track, BindingResult errors, HttpServletRequest request, HttpServletResponse response) {
        trackService.saveTrack(track);

        //Lista på tracks
        List<TrackEntity> tracks = trackService.getAllTracks();
        return new ModelAndView("tracklist", "tracks", tracks);
    }

    @RequestMapping(value = "/newtrack", method = RequestMethod.GET)
    public ModelAndView newTrack() {
        TrackEntity track = new TrackEntity();
        ModelMap map = new ModelMap();
        map.put("track", track);
        return new ModelAndView("track", map);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listAllTracks() {
        //Return to list of existing tracks
        List<TrackEntity> tracks = trackService.getAllTracks();
        return new ModelAndView("tracklist", "tracks", tracks);
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editTrack(@PathVariable String id, HttpServletRequest request) {
        TrackEntity track = null;
        try {
            track = trackService.getTrackById(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TrackNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ModelAndView("track", "track", track);
    }

    @RequestMapping(value = "/delete/{id}")
    public ModelAndView deleteTrack(@PathVariable String id, HttpServletRequest request) {
        try {
            trackService.deleteTrackById(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        List<TrackEntity> tracks = trackService.getAllTracks();
        return new ModelAndView("tracklist", "tracks", tracks);
    }

}
