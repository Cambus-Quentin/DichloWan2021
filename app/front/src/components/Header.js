import Logo from '../assets/dichlo.png'

const Header = (props) => {
    return (
        <div className='h-16 bg-gray-200 shadow-md'>
            <a href='/' className='flex items-center ml-4 w-max'>
                <img src={Logo} className='w-20' />
                <p className='text-3xl font-bold text-blue-600 ml-4'>DichloWAN</p>
            </a>
        </div>
    )
}

export default Header