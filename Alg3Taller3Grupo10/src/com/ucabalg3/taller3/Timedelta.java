package com.ucabalg3.taller3;

import java.time.Duration;


public class Timedelta {
	
	private Duration _self;
	
	public Timedelta(Duration duration) {
		this._self = duration;
	}

	public Duration self() {
		return _self;
	}

	@Override
	public String toString() {
		long seconds = this.self().getSeconds();
		long absSeconds = Math.abs(seconds);
		return String.format(
			"%d:%02d:%02d",
			absSeconds / 3600,
	        (absSeconds % 3600) / 60,
	        (absSeconds % 60)
		);
	}

}
