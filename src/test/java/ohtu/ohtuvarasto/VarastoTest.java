package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto varasto2;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varasto2 = new Varasto(10, 10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktori2LuoTaydenVaraston() {
        assertEquals(10, varasto2.getSaldo(), vertailuTarkkuus);
        assertEquals(10, varasto2.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void tulostusToimii() {
        String res = varasto.toString();
        String exp = "saldo = 0, vielä tilaa 10";
    }

    @Test
    public void hylataanVirheellinenVarasto() {
        Varasto test1 = new Varasto(-1);
        Varasto test2 = new Varasto(-1, -1);
        Varasto test3 = new Varasto(0, 1);
        assertEquals(0, test1.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, test2.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, test2.getSaldo(), vertailuTarkkuus);
        assertEquals(0, test3.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void otaVarastostaPoikkeukset() {
        varasto.lisaaVarastoon(10);
        assertEquals(0, varasto.otaVarastosta(-1), vertailuTarkkuus);
        assertEquals(10, varasto.otaVarastosta(11), vertailuTarkkuus);
    }

    @Test
    public void lisaaVarastoonPoikkeukset() {
        varasto.lisaaVarastoon(-1);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        varasto2.lisaaVarastoon(1);
        assertEquals(10, varasto2.getSaldo(), vertailuTarkkuus);
    }
}
