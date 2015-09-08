package com.mysamples;

import org.apache.commons.io.FilenameUtils;
import org.docx4j.Docx4J;
import org.docx4j.convert.out.FOSettings;
import org.docx4j.fonts.IdentityPlusMapper;
import org.docx4j.fonts.PhysicalFont;
import org.docx4j.fonts.PhysicalFonts;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class AppTest {

    public static File prjRoot() {
        return new File(System.getProperty("user.dir"));
    }

    @Test
    public void testPrjRoot() {
        assertThat(prjRoot().getAbsolutePath(),
                is("/Users/ntomura1/ghqroot/github.com/tomlla/docx4jsample"));
    }

    @Test
    public void testApp() throws Docx4JException, FileNotFoundException {
        final File srcDocxFile = new File(prjRoot(), "testfiles/LSM-1408A-SID-2810-S.docx");
        final File outputPdf = new File(prjRoot(), "test-results/" +
                FilenameUtils.getBaseName(srcDocxFile.getName()) + ".pdf");

        final WordprocessingMLPackage wmlp = WordprocessingMLPackage.load(srcDocxFile); // throws Docx4JException
        final IdentityPlusMapper fontMap = new IdentityPlusMapper();
        try {
            wmlp.setFontMapper(fontMap);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        PhysicalFont pf = PhysicalFonts.get("");

        final FOSettings foSettings = Docx4J.createFOSettings();
        foSettings.setWmlPackage(wmlp);

        final FileOutputStream fos = new FileOutputStream(outputPdf); // throws FileNotFoundException
        Docx4J.toFO(foSettings, fos, Docx4J.FLAG_EXPORT_PREFER_XSL);
    }

    static <B> B repeated(Function<Integer,B> f, int n) {
        for (int i = 0; i < n; i++) {

        }
        return null;
    }

    @Test
    public void testRepeated() {

    }
}
