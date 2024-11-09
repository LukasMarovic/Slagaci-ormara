import 'bootstrap/dist/css/bootstrap.min.css'

function CustomizedRecommendations(){
    return(
        <div className="feature-container m-4 p-0 d-flex align-items-center">
            <div className='feature-image p-0 d-flex'>
                <img src='src/assets/images/feature_img3.png' className='feature-illustration'></img>
            </div>
            <p className='feature-title fw-bold m-0 p-0 text-center d-flex flex-column justify-content-center'>
                Get Customized Recommendations<br/>
                <span className='feature-desc m-4'>
                    Don't know what to wear today?<br/>
                    Want to try a new outfit?<br/>
                    We will recommend you an outfit based on your location, weather and any other parameter you want!
                </span>
            </p>
        </div>
    );
}

export default CustomizedRecommendations