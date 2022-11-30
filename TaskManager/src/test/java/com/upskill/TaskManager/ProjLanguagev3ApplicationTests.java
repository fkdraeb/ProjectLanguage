package com.upskill.TaskManager;

import org.apache.tika.exception.TikaException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class ProjLanguagev3ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void main() throws TikaException, IOException {
		TaskManagerApplication.main(new String[] {});
	}
}
