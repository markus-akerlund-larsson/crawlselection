using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

[CreateAssetMenu(fileName = "New Card", menuName = "Card")]
public class Card : ScriptableObject
{
    public new string name;
    public int fightSymbols;
    public int moveSymbols;
    public int scoutSymbols;
    public int restSymbols;
    public int prepareSymbols;
    public int examineSymbols;
    public string text;
    public bool targeted = false;
    // Start is called before the first frame update
    void Start()
    {

    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
