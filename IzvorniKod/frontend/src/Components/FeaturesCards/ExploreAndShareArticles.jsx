import 'bootstrap/dist/css/bootstrap.min.css'

function ExploreAndShareArticles(){
    return(
        <div className="feature-container m-4 p-0 d-flex align-items-center">
            <div className='feature-image p-0 d-flex'>
                <img src='src/assets/images/feature_img1.png' className='feature-illustration'>
                </img>
            </div>
            <p className='feature-title fw-bold m-0 p-0 text-center d-flex flex-column justify-content-center'>
                Explore And Share Articles.<br/>
                <span className='feature-desc m-4'>
                    Find amazing clothing options to add to your virtual closet and collection. <br/>
                    Share your favorite items so everyone gets to enjoy them!
                </span>
            </p>
        </div>
    );
}

export default ExploreAndShareArticles