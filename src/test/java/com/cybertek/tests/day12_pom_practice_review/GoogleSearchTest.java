package com.cybertek.tests.day12_pom_practice_review;

import com.cybertek.pages.GoogleHomePage;
import com.cybertek.pages.GoogleResultPage;
import com.cybertek.utility.BrowserUtil;
import com.cybertek.utility.Driver;
import com.cybertek.utility.TestBase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoogleSearchTest extends TestBase {


    @Test
    public void testGoogleSearch() {

        GoogleHomePage homePage = new GoogleHomePage();
        homePage.goTo();
        // assert you are at the homepage
        assertTrue(   homePage.isAt()     );

        homePage.searchKeyword("SDET JOB");
        // assert the title starts with SDET JOB
        assertTrue( driver.getTitle().startsWith("SDET JOB")    ) ;
//        assertTrue( Driver.getDriver().getTitle().startsWith("SDET JOB")    ) ;
        // possibly wait little here
        GoogleResultPage resultPage = new GoogleResultPage();

        String resultText = resultPage.getResultCountText() ;
        System.out.println("resultText = " + resultText);

        resultPage.printAllSearchResultLinks();

        BrowserUtil.waitFor(4);
    }

    /**
     * Create 2 Pages Object for
     * - Google homepage
     *  Fields :
     *  - searchbox element
     *  - search button
     *  Methods
     *   - searchKeyWord
     *     - accept 1 string param as keyword
     *     - return nothing
     *     - should enter keyword and click search button
     *   - isAt
     *     - accept no param
     *     - return true if title match , false if not
     *   - goTo
     *      - void method with no param and navigate to google (use config reader for url)
     *
     * - Google SearchResultPage
     *   - Fields
     *      searchResultCount
     *      resultLinks (list of webelement )
     *   - Methods
     *      getResultCountText
     *      getAllResultLinkText
     */

}
