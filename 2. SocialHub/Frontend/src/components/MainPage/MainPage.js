import NavBar from "../NavBar/NavBar";
import LeftPanel from "./LeftPanel/LeftPanel";
import StatusBar from "./StatusBar/StatusBar";
import RightPanel from "./RightPanel/RightPanel";

export function MainPage() {
  return (
    <div className="app">
      <NavBar />

      <div className="app__body">
        <LeftPanel />
        <StatusBar />
        <RightPanel />

        {/* Feed */}
        {/* Widgets */}
      </div>
    </div>
  );
}
