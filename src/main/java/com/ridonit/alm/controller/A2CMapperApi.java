package com.ridonit.alm.controller;

import com.ridonit.alm.mapper.A2CMapper;
import com.ridonit.alm.mapper.C2AWatcher;
import com.ridonit.alm.mapper.calm.CalmProcess;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class A2CMapperApi {

	@PostMapping("/updateCloud")
	public void updateCloud(@RequestBody String json) {
		A2CMapper.updateCloudALM(json);
	}
	
	@PostMapping("/updateCloudTest")
	public void updateCloudTest(@RequestBody String title) {
		CalmProcess proc = new CalmProcess(null, title, title, title, title, title, title, title, title, title, null, null);
		A2CMapper.pushToCloud(proc);
	}
	
	@GetMapping("/startWatcher")
	public void startWatcher() {
		C2AWatcher watcher = new C2AWatcher();
		watcher.createThread(1000, null);
	}

}
