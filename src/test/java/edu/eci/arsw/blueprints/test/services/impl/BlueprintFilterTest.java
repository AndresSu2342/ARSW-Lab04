package edu.eci.arsw.blueprints.test.services.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.services.impl.RedundancyFilter;
import edu.eci.arsw.blueprints.services.impl.SubsamplingFilter;
import org.junit.Test;
import static org.junit.Assert.*;

public class BlueprintFilterTest {

    @Test
    public void testRedundancyFilter() {
        RedundancyFilter filter = new RedundancyFilter();
        Point[] points = {
                new Point(10, 10),
                new Point(10, 10),
                new Point(20, 20),
                new Point(30, 30),
                new Point(30, 30)
        };
        Blueprint bp = new Blueprint("testAuthor", "testBlueprint", points);

        Blueprint filteredBp = filter.filter(bp);

        // Verificar que el número de puntos únicos sea correcto
        assertEquals(3, filteredBp.getPoints().size());

        // Verificar cada punto uno por uno
        Point[] expectedPoints = { new Point(10, 10), new Point(20, 20), new Point(30, 30) };
        for (int i = 0; i < expectedPoints.length; i++) {
            assertEquals(expectedPoints[i].getX(), filteredBp.getPoints().get(i).getX());
            assertEquals(expectedPoints[i].getY(), filteredBp.getPoints().get(i).getY());
        }
    }

    @Test
    public void testSubsamplingFilter() {
        SubsamplingFilter filter = new SubsamplingFilter();
        Point[] points = {
                new Point(10, 10),
                new Point(20, 20),
                new Point(30, 30),
                new Point(40, 40)
        };
        Blueprint bp = new Blueprint("testAuthor", "testBlueprint", points);

        Blueprint filteredBp = filter.filter(bp);

        // Verificar que solo queden la mitad de los puntos
        assertEquals(2, filteredBp.getPoints().size());

        // Comparar los puntos seleccionados
        Point[] expectedPoints = { new Point(10, 10), new Point(30, 30) };
        for (int i = 0; i < expectedPoints.length; i++) {
            assertEquals(expectedPoints[i].getX(), filteredBp.getPoints().get(i).getX());
            assertEquals(expectedPoints[i].getY(), filteredBp.getPoints().get(i).getY());
        }
    }
}