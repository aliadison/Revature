import NavBar from '../NavBar/NavBar';
import LeftPanel from '../MainPage/LeftPanel/LeftPanel';
import StatusBar from './StatusBar/StatusBar';
import RightPanel from '../MainPage/RightPanel/RightPanel';

export function Quotes() {
    return(
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