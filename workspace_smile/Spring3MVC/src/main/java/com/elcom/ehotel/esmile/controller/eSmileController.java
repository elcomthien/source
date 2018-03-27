package com.elcom.ehotel.esmile.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elcom.ehotel.esmile.model.Params;
import com.elcom.ehotel.esmile.service.eSmileService;

@Controller
@RequestMapping("")
public class eSmileController {

	@Autowired
	private eSmileService esmileService;

	@RequestMapping(value = { "addlayout" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> addLayout(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.addLayout(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "editlayout" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> editLayout(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.editLayout(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "updatestatuslayout" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> updateStatusLayout(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.updateStatusLayout(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "deletelayout" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> deleteLayout(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.deleteLayout(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "getseqlayout" }, method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Object> getLayoutId() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.getLayoutId();
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "getlayout/{userid}/{layoutid}/{sessionid}" }, method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Object> getLayout(@PathVariable String userid, @PathVariable String layoutid,
			@PathVariable String sessionid) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.getLayout(userid, layoutid, sessionid);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "addpage" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> addPage(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.addPage(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "addobjectcontent" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> addObjectContent(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.addObjectContent(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "getlayout" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> getLayoutPost(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.getLayout(params.getUser_id(), params.getLayout_id(), params.getSession_id());
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "addobject" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> addObject(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.addObject(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "getsubjecttemplate" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> getSubjectTemplate(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.getSubjectTemplate(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "gettemplate" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> getTemplate(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.getTemplate(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "addaccount" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> addAccount(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		System.out.println(params);
		map = esmileService.addAccount(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "editaccount" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> editAccount(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.editAccount(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "postrating" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> postRating(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.postRating(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "getzone" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> getZone(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.getZone(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "resetpassword" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> resetPassword(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.resetPassword(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "deleteaccount" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> deleteAccount(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.deleteAccount(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "getsession" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> getSession(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.getSession(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "updatesession" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> updateSession(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.updateSession(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "getinfofilter" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> getInfoFilter(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.getInfoFilter(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "adddevice" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> addDevice(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.addDevice(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "getrole" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> getRole(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.getRole(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "getfeedbacklayout" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> getFeedbackLayout(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.getFeedbackLayout(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "addplugin" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> addPlugin(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.addPlugin(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "getplugin" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> getPlugin(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.getPlugin(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "editplugin" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> editPlugin(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.editPlugin(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "deleteplugin" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> deletePlugin(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.deletePlugin(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "addpluginemail" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> addPluginEmail(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.addPluginEmail(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "boxlogin" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> boxLogin(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.boxLogin(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "boxgetlink" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> boxGetLink(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.boxGetLink(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "postplugin" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> postPlugin(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.postPlugin(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "getfeedbackplugindetail" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> getFeedbackPluginDeatail(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.getFeedbackPluginDeatail(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "getfeedbackplugin" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> getFeedbackPlugin(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.getFeedbackPlugin(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "getpluginemail" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> getPluginEmail(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.getPluginEmail(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "getpluginbyid" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> getPluginById(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.getPluginById(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "getreportoverview" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> getReportOverview(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.getReportOverview(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "getreportresponse" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> getReportResponse(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.getReportResponse(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "getreportfeedback" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> getReportFeedback(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.getReportFeedback(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "getdevice" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> getListDevice(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.getListDevice(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "editdevice" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> editDevice(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.editDevice(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "deletedevice" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> deleteDevice(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.deleteDevice(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "addlayoutemail" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> addLayoutEmail(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.addLayoutEmail(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "getlocation" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> getListLocation(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.getListLocation(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "addrole" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> addRole(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.addRole(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "editrole" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> editRole(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.editRole(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "getaccount" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> getListAccount(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.getListAccount(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "getactivity" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> getListActivity(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.getListActivity(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "addzone" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> addZone(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.addZone(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "editzone" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> editZone(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.editZone(params);
		System.out.println("-----> Result: " + map);
		return map;
	}

	@RequestMapping(value = { "deletezone" }, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> deleteZone(@RequestBody Params params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = esmileService.deleteZone(params);
		System.out.println("-----> Result: " + map);
		return map;
	}
}
