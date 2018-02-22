package com.maerskdigital.task.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.maerskdigital.task.Application;

@Service
public class ShutdownService {
	
	@Async
	public void shutdown()
	{
		Application.ctx.close();
	}

}
