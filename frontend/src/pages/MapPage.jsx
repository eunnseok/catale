import Container from "../components/common/Container";
import styles from "./MapPage.module.css";
import Map from "../components/map/map";
import Header from "../components/common/Header";
import Nav from "../components/common/Nav";
import Storebox from "../components/map/Storebox";
import { markerdataB } from "../components/map/data/markerDataB";
import { markerdataG } from "../components/map/data/markerDataG";
import { useState } from "react";

export default function MapPage() {
  const [nowclick, setNowclick] = useState();

  return (
    <>
      <Container>
        <Header>
          <div className={styles.head}>
            <div className={styles.지역이름}>유성구</div>
            <div>근처</div>
          </div>
        </Header>
        <Map setNowclick={setNowclick} />
        <div className={styles.main}>
          <div className={styles.동이름}>봉명동</div>
          {markerdataB.map((data, index) => (
            <Storebox key={index} store={data} nowclick={nowclick} />
          ))}
          <div className={styles.동이름}>궁동</div>
          {markerdataG.map((data, index) => (
            <Storebox key={index} store={data} nowclick={nowclick} />
          ))}
        </div>
        <Nav num={2} />
      </Container>
    </>
  );
}
