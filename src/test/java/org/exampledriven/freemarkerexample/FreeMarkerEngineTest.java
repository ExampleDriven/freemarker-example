package org.exampledriven.freemarkerexample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class FreeMarkerEngineTest {

    @Inject
    private FreeMarkerEngine freeMarkerEngine;

    @Test
    public void testSqlTemplateWithoutFilmYear() {
        String expected =
            "select film_title\n" +
            "   from film_table\n"+
            "  where last_name  = \"ford\"\n"+
            "    and first_name = \"harrison\"\n";

        String result = freeMarkerEngine.process("sql/film.ftl", null);
        assertEquals(expected, result);

    }

    @Test
    public void testSqlTemplateWithFilmYear() {
        String expected =
            "select film_title\n" +
            "   from film_table\n"+
            "  where last_name  = \"ford\"\n"+
            "    and first_name = \"harrison\"\n"+
            "    and film_title in\n"+
            "         (select film_title\n"+
            "            from film_table\n"+
            "            where film_year equals 1984);";

        Map<String, String> model = new HashMap<>();
        model.put("film_year", "1984");

        String result = freeMarkerEngine.process("sql/film.ftl", model);
        assertEquals(expected, result);

    }

    @Test
    public void testSqlTemplateWithTitles() {
        String expected =
            "select film_title\n" +
            "   from film_table\n"+
            "  where last_name  = \"ford\"\n"+
            "    and first_name = \"harrison\"\n"+
            "    and film_title in (\n"+
            "    \"Star wars\",\n"+
            "    \"Indiana Jones\"\n"+
            "    )\n";
        Map<String, Object> model = new HashMap<>();
        List<String> titles = new LinkedList<>();
        titles.add("Star wars");
        titles.add("Indiana Jones");

        model.put("titles", titles);

        String result = freeMarkerEngine.process("sql/film.ftl", model);
        assertEquals(expected, result);

    }

}