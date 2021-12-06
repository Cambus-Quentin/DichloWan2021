import { LineChart, Line, ResponsiveContainer, CartesianGrid, XAxis, YAxis, Tooltip } from "recharts"
const data = [
    {
        name: 'Page A',
        uv: 4000,
    },
    {
        name: 'Page B',
        uv: 3000,
    },
    {
        name: 'Page C',
        uv: 2000,
    },
    {
        name: 'Page D',
        uv: 2780,
    },
    {
        name: 'Page E',
        uv: 1890,
    },
    {
        name: 'Page F',
        uv: 2390,
    },
    {
        name: 'Page G',
        uv: 3490,
    },
];

const data2 = [
    {
        name: 'Page A',
        uv: 5000,
    },
    {
        name: 'Page B',
        uv: 0,
    },
    {
        name: 'Page C',
        uv: 500,
    },
    {
        name: 'Page D',
        uv: 1000,
    },
    {
        name: 'Page E',
        uv: 2000,
    },
    {
        name: 'Page F',
        uv: 2000,
    },
    {
        name: 'Page G',
        uv: 2500,
    },
];


const Chart = (props) => {
    return (
        <ResponsiveContainer width="100%" height={400}>
            <LineChart data={props.date === "2021-12-06" ? data : data2}>
                <CartesianGrid strokeDasharray="3 3" />
                <XAxis dataKey="name" />
                <YAxis />
                <Tooltip />
                <Line type="monotone" dataKey="uv" stroke="#865474" />
            </LineChart>
        </ResponsiveContainer>

    )
}

export default Chart