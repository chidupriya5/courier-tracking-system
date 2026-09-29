document.addEventListener("DOMContentLoaded", () => {

    const menuToggle =
        document.getElementById("menuToggle");

    const navLinks =
        document.querySelector(".nav-links");


    if (menuToggle) {

        menuToggle.addEventListener("click", () => {

            navLinks.classList.toggle("mobile-open");

        });

    }


    /* ======================================
       SCROLL REVEAL
    ====================================== */

    const revealElements =
        document.querySelectorAll(
            ".service-card, .stat-card, .about-content"
        );


    const observer =
        new IntersectionObserver(
            entries => {

                entries.forEach(entry => {

                    if (entry.isIntersecting) {

                        entry.target.style.opacity = "1";

                        entry.target.style.transform =
                            "translateY(0)";

                    }

                });

            },
            {
                threshold: 0.15
            }
        );


    revealElements.forEach(element => {

        element.style.opacity = "0";

        element.style.transform =
            "translateY(25px)";

        element.style.transition =
            "all .7s ease";

        observer.observe(element);

    });


    /* ======================================
       TRACKING INPUT
    ====================================== */

    const trackingInput =
        document.querySelector(
            ".tracking-input input"
        );


    if (trackingInput) {

        trackingInput.addEventListener(
            "input",
            () => {

                trackingInput.value =
                    trackingInput.value.toUpperCase();

            }
        );

    }


    /* ======================================
       NAVBAR SHADOW
    ====================================== */

    window.addEventListener("scroll", () => {

        const navbar =
            document.querySelector(".navbar");

        if (window.scrollY > 20) {

            navbar.style.boxShadow =
                "0 5px 25px rgba(20,20,50,.06)";

        } else {

            navbar.style.boxShadow = "none";

        }

    });

});