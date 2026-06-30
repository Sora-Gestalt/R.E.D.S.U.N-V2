import SlideBar from "../components/SlideBar";
import Footer from "../components/Footer";
import LatestTimeLine from "../components/LatestTimeLine";
import HeroSection from "../components/HeroSection";


export default function Home(){
    return(
        <>
            <SlideBar></SlideBar>
            <HeroSection></HeroSection>
            <LatestTimeLine></LatestTimeLine>
            <Footer></Footer>
        </>
    );
}