if (typeof renderMathInElement == 'undefined') {
    var getScript = t=>{
            var e = document.createElement('script');
            e.defer = !0,
                e.crossOrigin = 'anonymous',
                Object.keys(t).forEach(n=>{
                        e[n] = t[n]
                    }
                ),
                document.body.appendChild(e)
        }
    ;
    getScript({
        src: 'https://cdn.jsdelivr.net/npm/katex@0.13.0/dist/katex.min.js',
        integrity: 'sha256-YTW9cMncW/ZQMhY69KaUxIa2cPTxV87Uh627Gf5ODUw=',
        onload: ()=>{
            getScript({
                src: 'https://cdn.jsdelivr.net/npm/katex@0.13.0/dist/contrib/mhchem.min.js',
                integrity: 'sha256-yzSfYeVsWJ1x+2g8CYHsB/Mn7PcSp8122k5BM4T3Vxw=',
                onload: ()=>{
                    getScript({
                        src: 'https://cdn.jsdelivr.net/npm/katex@0.13.0/dist/contrib/auto-render.min.js',
                        integrity: 'sha256-fxJzNV6hpc8tgW8tF0zVobKa71eTCRGTgxFXt1ZpJNM=',
                        onload: ()=>{
                            renderKaTex()
                        }
                    })
                }
            })
        }
    })
} else
    renderKaTex();
function renderKaTex() {
    renderMathInElement(document.body, {
        delimiters: [{
            left: "$$",
            right: "$$",
            display: !0
        }, {
            left: "\\[",
            right: "\\]",
            display: !0
        }, {
            left: "$",
            right: "$",
            display: !1
        }, {
            left: "\\(",
            right: "\\)",
            display: !1
        }]
    })
}
typeof MathJax == 'undefined' ? (window.MathJax = {
    loader: {
        load: ['[tex]/mhchem']
    },
    tex: {
        inlineMath: {
            '[+]': [['$', '$']]
        },
        tags: 'ams',
        packages: {
            '[+]': ['mhchem']
        }
    }
},
function() {
    var e = document.createElement('script');
    e.src = 'https://cdn.jsdelivr.net/npm/mathjax@3.1.2/es5/tex-mml-chtml.js',
        e.defer = !0,
        document.head.appendChild(e)
}()) : (MathJax.texReset(),
MathJax.typeset())
let imgNodes = document.querySelectorAll('div.post-body img');
imgNodes = Array.from(imgNodes).filter(e=>e.parentNode.tagName !== "A"),
    mediumZoom(imgNodes, {
        background: 'hsla(var(--color-bg-h), var(--color-bg-s), var(--color-bg-l), 0.95)'
    })