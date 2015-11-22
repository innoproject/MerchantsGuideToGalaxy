package com.thougthworks.guide.to.galaxy.parser.test;

import java.io.InputStream;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.guide.to.galaxy.build.BuildFromPattern;
import com.thoughtworks.guide.to.galaxy.exceptions.ApplicationLevelException;
import com.thoughtworks.guide.to.galaxy.input.parser.ParseInputFromStream;
import com.thoughtworks.guide.to.galaxy.reader.InputReader;
import com.thoughtworks.guide.to.galaxy.rules.ConversionRuleParser;
import com.thoughtworks.guide.to.galaxy.rules.GalaxyRules;
import com.thoughtworks.guide.to.galaxy.service.interfaces.Parser;

/**
 * For a given input file this class will test the functionality of the conversion on the actual
 * data from the file
 * 
 * @author viveksingh
 */
public class InputParserTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(InputParserTest.class);
	
	private Parser _parser;
	
	@Before public void beforeTest() {
		GalaxyRules _galaxyRules = ConversionRuleParser.getInstance().getGalaxyRules();
		try {
			InputStream _inputStream = InputReader.getStream("validInput-test.txt");
			_parser = new ParseInputFromStream(_inputStream, _galaxyRules);
		} catch (ApplicationLevelException _appException) {
			LOGGER.error(_appException.getMessage());
		}
	}
	
	@Test public void outputTest() throws Exception {
		BuildFromPattern _buildFromPattern = _parser.parse();
		Assert.assertNotNull(_buildFromPattern.getAnswerMapper());
		
		Map<String, Object> _answerMapper = _buildFromPattern.getAnswerMapper();
		Assert.assertEquals(4, _answerMapper.size());
		
		Assert.assertEquals("trot grot trot pish tegj glob prok", (Float)_answerMapper.get("trot grot trot pish tegj glob prok"), new Float(1944.0));
		Assert.assertEquals("glob prok trot Silver", (Float)_answerMapper.get("glob prok trot Silver"), new Float(76.0));
		Assert.assertEquals("glob prok pish tegj Gold", (Float)_answerMapper.get("glob prok pish tegj Gold"), new Float(616.0));
		Assert.assertEquals("glob prok bleek Iron", (Float)_answerMapper.get("glob prok bleek Iron"), new Float(60.0));
	}
}