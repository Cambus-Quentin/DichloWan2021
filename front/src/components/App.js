import Header from './Header'
import RenderedChart from './RenderedChart';

function App() {
  return (
    <div>
      <Header />
      <div className="grid grid-cols-4">
        <div className="col-span-2">
          <RenderedChart />
        </div>
        <div className="col-span-2">
          <RenderedChart />
        </div>
        <div className="col-span-2 col-start-2">
          <RenderedChart />
        </div>
      </div>
    </div>
  );
}

export default App;
