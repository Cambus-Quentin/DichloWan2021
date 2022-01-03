import Header from './Header'
import RenderedChart from './RenderedChart';

function App() {
  return (
    <div>
      <Header />
      <div className="grid grid-cols-4">
        <div className="col-span-2">
          <RenderedChart accuracy="daily"/>
        </div>
        <div className="col-span-2">
          <RenderedChart accuracy="weekly"/>
        </div>
        <div className="col-span-2 col-start-2 relative -top-12">
          <RenderedChart accuracy="monthly"/>
        </div>
      </div>
    </div>
  );
}

export default App;
